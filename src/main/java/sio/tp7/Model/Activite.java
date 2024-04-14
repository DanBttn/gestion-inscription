package sio.tp7.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static sio.tp7.Tools.ConnexionBDD.cnx;

public class Activite
{
    private int idActivite;
    private String nomActivite;
    private PreparedStatement ps;
    private ResultSet rs;

    public Activite()
    {

    }
    public Activite(int unId, String unNom)
    {
        idActivite = unId;
        nomActivite = unNom;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }



}
