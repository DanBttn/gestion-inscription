package sio.tp7.Services;



import sio.tp7.Model.Agent;
import sio.tp7.Model.AgentPresent;
import sio.tp7.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceAgent {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceAgent() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Agent> GetAllAgentsNonInscritsFormation(String idFormation) throws SQLException {
        ArrayList<Agent> lesAgents = new ArrayList<>();
        ps = cnx.prepareStatement("select agent.code, agent.nom, agent.prenom from agent WHERE agent.code NOT IN(select inscription.codeAgent from inscription where inscription.numeroFormation = ?)");
        ps.setString(1, idFormation);
        rs = ps.executeQuery();
        while (rs.next())
        {
            Agent agent =  new Agent(rs.getString(1), rs.getString(2), rs.getString(3));
            lesAgents.add(agent);
        }
        ps.close();
        rs.close();
        return lesAgents;
    }

    public ArrayList<AgentPresent> GetAllAgentsInscritsFormation(String idFormation) throws SQLException {
        ArrayList<AgentPresent> lesAgents = new ArrayList<>();

        ps = cnx.prepareStatement("SELECT agent.code, agent.nom, agent.prenom,inscription.presence FROM agent  inner JOIN inscription on agent.code = inscription.codeAgent WHERE inscription.numeroFormation = ? ");
        ps.setString(1, idFormation);
        rs = ps.executeQuery();
        while (rs.next())
        {
            AgentPresent agentPresent = new AgentPresent(rs.getString("code"), rs.getString("nom"), rs.getString("prenom"), rs.getBoolean("presence"));
            lesAgents.add(agentPresent);
        }
        ps.close();
        rs.close();

        return lesAgents;
    }
}
