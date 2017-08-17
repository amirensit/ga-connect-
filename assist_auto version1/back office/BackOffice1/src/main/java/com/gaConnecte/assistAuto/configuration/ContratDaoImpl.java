package com.gaConnecte.assistAuto.configuration;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.gaConnecte.assistAuto.entities.Contrat;

@Repository
public class ContratDaoImpl extends JdbcDaoSupport implements ContratDAO {

	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	
	@Override
	public void insert(List<? extends Contrat> contrats) {
		
		String sql = "INSERT INTO public.contrat " + "(id_contrat,addresse,date_debut,date_fin,nom,num_contrat,prenom,code_marque,code_pack,code_ville) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
		
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int C_ID_contrat = 1;
				int C_ADD_contrat=2;
				int C_DATEDEBUT_contrat=3;
				int C_DATEFIN_contrat = 4;
				int C_NOM_contrat = 5;
				int C_NUM_contrat = 6 ;
				int C_PRENOM_contrat = 7;
				int C_CODEMARQUE_contrat = 8;
				int C_CODEPACK_contrat = 9;
				int C_CODEVILLE_contrat = 10;
				Contrat contrat = contrats.get(i);
				ps.setLong(C_ID_contrat, contrat.getId_contrat());
				ps.setString(C_ADD_contrat, contrat.getAddresse());
				ps.setDate(C_DATEDEBUT_contrat, new java.sql.Date(contrat.getDate_debut().getTime()));
				ps.setDate(C_DATEFIN_contrat, new java.sql.Date(contrat.getDate_fin().getTime()));
				ps.setString(C_NOM_contrat, contrat.getNom());
				ps.setLong(C_NUM_contrat, contrat.getNum_contrat());
				ps.setString(C_PRENOM_contrat, contrat.getPrenom());
				ps.setLong(C_CODEMARQUE_contrat, 1);
				ps.setLong(C_CODEPACK_contrat, 1);
				ps.setLong(C_CODEVILLE_contrat, 35);
			}

			public int getBatchSize() {
				return contrats.size();
			}
		});
		
		
	}

}
