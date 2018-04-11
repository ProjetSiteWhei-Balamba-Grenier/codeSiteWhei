import fr.hei.iti.sitewebwhei.dao.MembreDao;
import fr.hei.iti.sitewebwhei.dao.impl.DataSourceProvider;
import fr.hei.iti.sitewebwhei.dao.impl.MembreDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Membre;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MembreDaoTestCase {

    private MembreDao membreDao = new MembreDaoImpl();

    @Before
    public void initDb() throws Exception {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            // Initialisation de la table membre
            stmt.executeUpdate("DELETE FROM membre");
            // Insertion de deux membres
            stmt.executeUpdate(
                    "INSERT INTO `membre`(`membre_id`,`prenom`, nom, poste, description, url_image) "
                            + "VALUES (1, 'monPrenom1', 'monNom1', 'monPoste1', 'maDescription1', 'monUrl1')");
            stmt.executeUpdate(
                    "INSERT INTO `membre`(`membre_id`,`prenom`, nom, poste, description, url_image) "
                            + "VALUES (2, 'monPrenom2', 'monNom2', 'monPoste2', 'maDescription2', 'monUrl2')");
        }
    }

    @Test
    public void ShouldListMembre(){
        // Test de la fonction
        List<Membre> membres = membreDao.listMembre();
        // Verification du resultat
        assertThat(membres).hasSize(2);
    }

    @Test
    public void shouldAddMembre() throws Exception {
        // Creation d'un membre
        Membre newMembre = new Membre(null, "monPrenom", "monNom", "monPoste", "maDescription", "monUrl");
        // Test de la fonction
        Membre createdMembre = membreDao.addMembre(newMembre);
        // Verification que le membre a bien ete creee
        assertThat(createdMembre).isNotNull();
        assertThat(createdMembre.getId()).isNotNull();
        assertThat(createdMembre.getId()).isGreaterThan(0);
        assertThat(createdMembre.getPrenom()).isEqualTo("monPrenom");
        assertThat(createdMembre.getNom()).isEqualTo("monNom");
        assertThat(createdMembre.getPoste()).isEqualTo("monPoste");
        assertThat(createdMembre.getDescription()).isEqualTo("maDescription");
        assertThat(createdMembre.getUrlImage()).isEqualTo("monUrl");

        // Verification que le membre a bien ete ajoute a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM membre WHERE prenom = 'monPrenom'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("membre_id")).isEqualTo(createdMembre.getId());
                assertThat(rs.getString("prenom")).isEqualTo("monPrenom");
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("poste")).isEqualTo("monPoste");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteMembre() throws Exception {
        // Test de la fonction
        membreDao.deleteMembre(1);

        // Verification du resultat dans la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM membre WHERE membre_id=?")) {
            statement.setInt(1, 1);
            try (ResultSet rs = statement.executeQuery()) {
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldModifierMembre() throws Exception{
        // Creation d'un membre
        Membre membre = new Membre(1, "monPrenom", "monNom", "monPoste", "maDescription", "monUrl");
        // Test de la fonction
        membreDao.modifierMembre(membre);

        // Verification du resultat dans la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM membre WHERE membre_id = '1';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("membre_id")).isEqualTo(1);
                assertThat(rs.getString("prenom")).isEqualTo("monPrenom");
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("poste")).isEqualTo("monPoste");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }

    }

    @Test
    public void shouldGetMembre() {
        // Test de la fonction
        Membre membre = membreDao.getMembre(1);
        // Verification du resultat
        assertThat(membre).isNotNull();
        assertThat(membre.getId()).isEqualTo(1);
        assertThat(membre.getPrenom()).isEqualTo("monPrenom1");
        assertThat(membre.getNom()).isEqualTo("monNom1");
        assertThat(membre.getPoste()).isEqualTo("monPoste1");
        assertThat(membre.getDescription()).isEqualTo("maDescription1");
        assertThat(membre.getUrlImage()).isEqualTo("monUrl1");
    }
}