package sio.tp7.Model;

public class Formation
{
    private String idFormation;
    private String nomFormation;

    public Formation()
    {

    }
    public Formation(String unId, String unNom)
    {
        idFormation = unId;
        nomFormation = unNom;
    }

    public String getIdFormation() {
        return idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }
}
