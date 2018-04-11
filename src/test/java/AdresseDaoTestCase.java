import fr.hei.iti.sitewebwhei.dao.AdresseDao;
import fr.hei.iti.sitewebwhei.dao.impl.AdresseDaoImpl;
import fr.hei.iti.sitewebwhei.dao.impl.DataSourceProvider;
import fr.hei.iti.sitewebwhei.entities.Adresse;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdresseDaoTestCase {

    private AdresseDao adresseDao = new AdresseDaoImpl();

    @Before
    public void initDb() throws Exception {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            // Initialisation de la table adresse
            stmt.executeUpdate("DELETE FROM adresse");
            // Insertion de deux adresses dans la table
            stmt.executeUpdate(
                    "INSERT INTO `adresse`(`adresse_id`,`nom`, adresse_type, adresse, horaires, description, url_image) "
                            + "VALUES (1, 'monNom1', 'monType1', 'monAdresse1','mesHoraires1', 'maDescription1', 'monUrl1')");
            stmt.executeUpdate(
                    "INSERT INTO `adresse`(`adresse_id`,`nom`, adresse_type, adresse, horaires, description, url_image) "
                            + "VALUES (2, 'monNom2', 'monType2', 'monAdresse2','mesHoraires2', 'maDescription2', 'monUrl2')");
        }
    }

    @Test
    public void ShouldListAdresse(){
        // Test de la fonction
        List<Adresse> adresses = adresseDao.listAdresse();
        // Verification du resultat
        assertThat(adresses).hasSize(2);
    }

    @Test
    public void shouldAddAdresse() throws Exception {
        // Creation d'une adresse
        Adresse newAdresse = new Adresse(null, "monNom", "monType", "monAdresse", "mesHoraires", "maDescription", "monUrl");
        // Test de la fonction
        Adresse createdAdresse = adresseDao.addAdresse(newAdresse);
        // Verification que l'adresse a bien ete creee
        assertThat(createdAdresse).isNotNull();
        assertThat(createdAdresse.getId()).isNotNull();
        assertThat(createdAdresse.getId()).isGreaterThan(0);
        assertThat(createdAdresse.getNom()).isEqualTo("monNom");
        assertThat(createdAdresse.getType()).isEqualTo("monType");
        assertThat(createdAdresse.getAdresse()).isEqualTo("monAdresse");
        assertThat(createdAdresse.getHoraires()).isEqualTo("mesHoraires");
        assertThat(createdAdresse.getDescription()).isEqualTo("maDescription");
        assertThat(createdAdresse.getUrlImage()).isEqualTo("monUrl");

        // Verification que l'adresse a bien ete ajoutee a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM adresse WHERE nom = 'monNom'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("adresse_id")).isEqualTo(createdAdresse.getId());
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("adresse_type")).isEqualTo("monType");
                assertThat(rs.getString("adresse")).isEqualTo("monAdresse");
                assertThat(rs.getString("horaires")).isEqualTo("mesHoraires");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteAdresse() throws Exception {
        // Test de la fonction
        adresseDao.deleteAdresse(1);

        // Verification du resultat dans la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM adresse WHERE adresse_id=?")) {
            statement.setInt(1, 1);
            try (ResultSet rs = statement.executeQuery()) {
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldModifierAdresse() throws Exception{
        // Cration d'une adresse
        Adresse adresse = new Adresse(1, "monNom", "monType", "monAdresse", "mesHoraires", "maDescription", "monUrl");
        // Test de la fonction
        adresseDao.modifierAdresse(adresse);

        //Verification du resultat dans la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM adresse WHERE adresse_id = '1';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("adresse_id")).isEqualTo(1);
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("adresse_type")).isEqualTo("monType");
                assertThat(rs.getString("adresse")).isEqualTo("monAdresse");
                assertThat(rs.getString("horaires")).isEqualTo("mesHoraires");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }

    }

    @Test
    public void shouldGetAdresse() {
        // Creation d'une adresse
        Adresse adresse = adresseDao.getAdresse(1);
        // Verification du resultat
        assertThat(adresse).isNotNull();
        assertThat(adresse.getId()).isEqualTo(1);
        assertThat(adresse.getNom()).isEqualTo("monNom1");
        assertThat(adresse.getType()).isEqualTo("monType1");
        assertThat(adresse.getAdresse()).isEqualTo("monAdresse1");
        assertThat(adresse.getHoraires()).isEqualTo("mesHoraires1");
        assertThat(adresse.getDescription()).isEqualTo("maDescription1");
        assertThat(adresse.getUrlImage()).isEqualTo("monUrl1");
        }
}
