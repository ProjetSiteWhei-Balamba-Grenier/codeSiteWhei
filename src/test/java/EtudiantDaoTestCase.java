import fr.hei.iti.sitewebwhei.dao.EtudiantDao;
import fr.hei.iti.sitewebwhei.dao.impl.DataSourceProvider;
import fr.hei.iti.sitewebwhei.dao.impl.EtudiantDaoImpl;
import fr.hei.iti.sitewebwhei.entities.Etudiant;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EtudiantDaoTestCase {

    private EtudiantDao etudiantDao = new EtudiantDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM etudiant");
            stmt.executeUpdate(
                    "INSERT INTO `etudiant`(`etudiant_id`,`prenom`, nom, telephone) "
                            + "VALUES (1, 'monPrenom1', 'monNom1', '0600000001')");
            stmt.executeUpdate(
                    "INSERT INTO `etudiant`(`etudiant_id`,`prenom`, nom, telephone) "
                            + "VALUES (2, 'monPrenom2', 'monNom2', '0600000002')");
        }
    }

    @Test
    public void ShouldListEtudiant(){
        // WHEN
        List<Etudiant> etudiants = etudiantDao.listEtudiant();
        // THEN
        assertThat(etudiants).hasSize(2);
    }

    @Test
    public void shouldAddEtudiant() throws Exception {
        // GIVEN
        Etudiant newEtudiant = new Etudiant(null, "monPrenom", "monNom", "0600000000");
        // WHEN
        Etudiant createdEtudiant = etudiantDao.addEtudiant(newEtudiant);
        // THEN
        assertThat(createdEtudiant).isNotNull();
        assertThat(createdEtudiant.getId()).isNotNull();
        assertThat(createdEtudiant.getId()).isGreaterThan(0);
        assertThat(createdEtudiant.getPrenom()).isEqualTo("monPrenom");
        assertThat(createdEtudiant.getNom()).isEqualTo("monNom");
        assertThat(createdEtudiant.getTelephone()).isEqualTo("0600000000");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM etudiant WHERE prenom = 'monPrenom'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("etudiant_id")).isEqualTo(createdEtudiant.getId());
                assertThat(rs.getString("prenom")).isEqualTo("monPrenom");
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("telephone")).isEqualTo("0600000000");

                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteEtudiant() throws Exception {
        etudiantDao.deleteEtudiant(1);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM etudiant WHERE etudiant_id=?")) {
            statement.setInt(1, 1);
            try (ResultSet rs = statement.executeQuery()) {
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldModifierEtudiant() throws Exception{
        Etudiant etudiant = new Etudiant(1, "monPrenom", "monNom", "0600000000");
        etudiantDao.modifierEtudiant(etudiant);

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM etudiant WHERE etudiant_id = '1';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("etudiant_id")).isEqualTo(1);
                assertThat(rs.getString("prenom")).isEqualTo("monPrenom");
                assertThat(rs.getString("nom")).isEqualTo("monNom");
                assertThat(rs.getString("telephone")).isEqualTo("0600000000");

                assertThat(rs.next()).isFalse();
            }
        }

    }

    @Test
    public void shouldGetEtudiant() {
        // WHEN
        Etudiant etudiant = etudiantDao.getEtudiant(1);
        // THEN
        assertThat(etudiant).isNotNull();
        assertThat(etudiant.getId()).isEqualTo(1);
        assertThat(etudiant.getPrenom()).isEqualTo("monPrenom1");
        assertThat(etudiant.getNom()).isEqualTo("monNom1");
        assertThat(etudiant.getTelephone()).isEqualTo("0600000001");
        }
}
