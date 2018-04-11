import fr.hei.iti.sitewebwhei.dao.MoyenDeContactDao;
import fr.hei.iti.sitewebwhei.dao.impl.DataSourceProvider;
import fr.hei.iti.sitewebwhei.dao.impl.MoyenDeContactDaoImpl;
import fr.hei.iti.sitewebwhei.entities.MoyenDeContact;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoyenDeContactDaoTestCase {

    private MoyenDeContactDao moyenDeContactDao = new MoyenDeContactDaoImpl();

    @Before
    public void initDb() throws Exception {
        // Connexion a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            // Initialisation de la table moyen_de_contact
            stmt.executeUpdate("DELETE FROM moyen_de_contact");
            // Insertion de deux moyens de contact
            stmt.executeUpdate(
                    "INSERT INTO `moyen_de_contact`(`moyen_de_contact_id`,`nom`, `precision`, `url_precision`, description, url_image) "
                            + "VALUES (1, 'monNom1', 'maPrecision1', 'monUrlPrecision1', 'maDescription1', 'monUrl1')");
            stmt.executeUpdate(
                    "INSERT INTO `moyen_de_contact`(`moyen_de_contact_id`,`nom`, `precision`, `url_precision`, description, url_image) "
                            + "VALUES (2, 'monNom2', 'maPrecision2', 'monUrlPrecision2', 'maDescription2', 'monUrl2')");
        }
    }

    @Test
    public void ShouldListMoyenDeContact(){
        // Test de la fonction
        List<MoyenDeContact> moyenDeContact = moyenDeContactDao.listMoyenDeContact();
        // Verification du resultat
        assertThat(moyenDeContact).hasSize(2);
    }

    @Test
    public void shouldAddMoyenDeContact() throws Exception {
        // Creation d'un moyen de contact
        MoyenDeContact newMoyenDeContact = new MoyenDeContact(null, "monNom", "maPrecision", "monUrlPrecision","maDescription", "monUrl");
        // Test de a fonction
        MoyenDeContact createdMoyenDeContact = moyenDeContactDao.addMoyenDeContact(newMoyenDeContact);
        // Verification que le moyen de contact a bien ete cree
        assertThat(createdMoyenDeContact).isNotNull();
        assertThat(createdMoyenDeContact.getId()).isNotNull();
        assertThat(createdMoyenDeContact.getId()).isGreaterThan(0);
        assertThat(createdMoyenDeContact.getNom()).isEqualTo("monNom");
        assertThat(createdMoyenDeContact.getPrecision()).isEqualTo("maPrecision");
        assertThat(createdMoyenDeContact.getUrlPrecision()).isEqualTo("monUrlPrecision");
        assertThat(createdMoyenDeContact.getDescription()).isEqualTo("maDescription");
        assertThat(createdMoyenDeContact.getUrlImage()).isEqualTo("monUrl");

        // Verification que le moyen de contact a bien ete ajoute a la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM moyen_de_contact WHERE nom = 'monNom'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("moyen_de_contact_id")).isEqualTo(createdMoyenDeContact.getId());
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("precision")).isEqualTo("maPrecision");
                assertThat(rs.getString("url_precision")).isEqualTo("monUrlPrecision");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteMoyenDeContact() throws Exception {
        // Test de la fonction
        moyenDeContactDao.deleteMoyenDeContact(1);

        // Verification du resultat dans la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM moyen_de_contact WHERE moyen_de_contact_id=?")) {
            statement.setInt(1, 1);
            try (ResultSet rs = statement.executeQuery()) {
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldModifierMoyenDeContact() throws Exception{
        // Creation d'un moyen de contact
        MoyenDeContact moyenDeContact = new MoyenDeContact(1, "monNom", "maPrecision", "monUrlPrecision", "maDescription", "monUrl");
        // Test de la fonction
        moyenDeContactDao.modifierMoyenDeContact(moyenDeContact);

        // Verification du resultat dans la base de donnees
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM moyen_de_contact WHERE moyen_de_contact_id = '1';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("moyen_de_contact_id")).isEqualTo(1);
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("precision")).isEqualTo("maPrecision");
                assertThat(rs.getString("url_precision")).isEqualTo("monUrlPrecision");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }

    }

    @Test
    public void shouldGetMoyenDeContact() {
        // Test de la fonction
        MoyenDeContact moyenDeContact = moyenDeContactDao.getMoyenDeContact(1);
        // Verification du resultat dans la base donnees
        assertThat(moyenDeContact).isNotNull();
        assertThat(moyenDeContact.getId()).isEqualTo(1);
        assertThat(moyenDeContact.getNom()).isEqualTo("monNom1");
        assertThat(moyenDeContact.getPrecision()).isEqualTo("maPrecision1");
        assertThat(moyenDeContact.getUrlPrecision()).isEqualTo("monUrlPrecision1");
        assertThat(moyenDeContact.getDescription()).isEqualTo("maDescription1");
        assertThat(moyenDeContact.getUrlImage()).isEqualTo("monUrl1");
    }
}
