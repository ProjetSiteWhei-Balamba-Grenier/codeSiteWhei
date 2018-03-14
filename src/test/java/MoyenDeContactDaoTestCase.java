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
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM moyen_de_contact");
            stmt.executeUpdate(
                    "INSERT INTO `moyen_de_contact`(`moyen_de_contact_id`,`nom`, `precision`, description, url_image) "
                            + "VALUES (1, 'monNom1', 'maPrecision1', 'maDescription1', 'monUrl1')");
            stmt.executeUpdate(
                    "INSERT INTO `moyen_de_contact`(`moyen_de_contact_id`,`nom`, `precision`, description, url_image) "
                            + "VALUES (2, 'monNom2', 'maPrecision2', 'maDescription2', 'monUrl2')");
        }
    }

    @Test
    public void ShouldListMoyenDeContact(){
        // WHEN
        List<MoyenDeContact> moyenDeContact = moyenDeContactDao.listMoyenDeContact();
        // THEN
        assertThat(moyenDeContact).hasSize(2);
    }

    @Test
    public void shouldAddMoyenDeContact() throws Exception {
        // GIVEN
        MoyenDeContact newMoyenDeContact = new MoyenDeContact(null, "monNom", "maPrecision", "maDescription", "monUrl");
        // WHEN
        MoyenDeContact createdMoyenDeContact = moyenDeContactDao.addMoyenDeContact(newMoyenDeContact);
        // THEN
        assertThat(createdMoyenDeContact).isNotNull();
        assertThat(createdMoyenDeContact.getId()).isNotNull();
        assertThat(createdMoyenDeContact.getId()).isGreaterThan(0);
        assertThat(createdMoyenDeContact.getNom()).isEqualTo("monNom");
        assertThat(createdMoyenDeContact.getPrecision()).isEqualTo("maPrecision");
        assertThat(createdMoyenDeContact.getDescription()).isEqualTo("maDescription");
        assertThat(createdMoyenDeContact.getUrlImage()).isEqualTo("monUrl");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM moyen_de_contact WHERE nom = 'monNom'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("moyen_de_contact_id")).isEqualTo(createdMoyenDeContact.getId());
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("precision")).isEqualTo("maPrecision");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteMoyenDeContact() throws Exception {
        moyenDeContactDao.deleteMoyenDeContact(1);

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
        MoyenDeContact moyenDeContact = new MoyenDeContact(1, "monNom", "maPrecision", "maDescription", "monUrl");
        moyenDeContactDao.modifierMoyenDeContact(moyenDeContact);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM moyen_de_contact WHERE moyen_de_contact_id = '1';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("moyen_de_contact_id")).isEqualTo(1);
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("precision")).isEqualTo("maPrecision");
                assertThat(rs.getString("description")).isEqualTo("maDescription");
                assertThat(rs.getString("url_image")).isEqualTo("monUrl");

                assertThat(rs.next()).isFalse();
            }
        }

    }

    @Test
    public void shouldGetMoyenDeContact() {
        // WHEN
        MoyenDeContact moyenDeContact = moyenDeContactDao.getMoyenDeContact(1);
        // THEN
        assertThat(moyenDeContact).isNotNull();
        assertThat(moyenDeContact.getId()).isEqualTo(1);
        assertThat(moyenDeContact.getNom()).isEqualTo("monNom1");
        assertThat(moyenDeContact.getPrecision()).isEqualTo("maPrecision1");
        assertThat(moyenDeContact.getDescription()).isEqualTo("maDescription1");
        assertThat(moyenDeContact.getUrlImage()).isEqualTo("monUrl1");
    }
}
