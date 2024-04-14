package sio.tp7.Services;



import sio.tp7.Model.Activite;
import sio.tp7.Tools.ConnexionBDD;

import java.security.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceActivite {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceActivite() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Activite> GetAllActivites() throws SQLException {
        ArrayList<Activite> lesActivites = new ArrayList<>();
        ps = cnx.prepareStatement("select activite.numero, activite.libelle from activite");
        rs = ps.executeQuery();
        while (rs.next())
        {
            Activite activite = new Activite(rs.getInt(1), rs.getString(2));
            lesActivites.add(activite);
        }

        rs.close();
        rs.close();
        return lesActivites;
    }
}
