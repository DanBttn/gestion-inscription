package sio.tp7.Services;



import sio.tp7.Model.Activite;
import sio.tp7.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceInscription {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceInscription() {
        cnx = ConnexionBDD.getCnx();
    }

    public void InsererInscription(String idFormation, String idAgent) throws SQLException {
        ps = cnx.prepareStatement("INSERT INTO inscription values(?,?,0) ");
        //on met le 0 pour la presence
        ps.setString(1,idFormation);
        ps.setString(2,idAgent);
        ps.executeUpdate();
        ps.close();
    }

    public void ModifierPresence(String idFormation, String idAgent, boolean present) throws SQLException {
        ps = cnx.prepareStatement("UPDATE inscription set presence = ? WHERE numeroFormation = ? and codeAgent = ?");
        ps.setString(1,idFormation);
        ps.setString(2,idAgent);
        ps.setBoolean(3,present);
        //executeQuery on l'utilise lorsqu'on met un SELECT
        ps.executeUpdate();
    }
    public void DesinscrireAgentByIdAgent(String idAgent) throws SQLException {
        ps = cnx.prepareStatement("delete from inscription where codeAgent=?");
        ps.setString(1, idAgent);
        ps.executeUpdate();
        ps.close();
    }


}
