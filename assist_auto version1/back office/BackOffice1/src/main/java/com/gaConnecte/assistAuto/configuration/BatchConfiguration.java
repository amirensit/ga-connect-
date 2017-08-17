package com.gaConnecte.assistAuto.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.gaConnecte.assistAuto.entities.Contrat;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public ContratDAO contratDAO;
	
	
	@Bean
	public FlatFileItemReader<Contrat> reader()
	{
		FlatFileItemReader<Contrat> reader = new FlatFileItemReader<Contrat>();
	
		reader.setResource(new ClassPathResource("import/shema1.csv"));
		reader.setLineMapper(new DefaultLineMapper<Contrat>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				
			setNames(new String[] {"id_contrat","addresse","date_debut","date_fin","nom","num_contrat","prenom","code_marque","code_pack","code_ville"} );
			
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Contrat>() {{
				setTargetType(Contrat.class);
			}});
		}});
		return reader;
	}
	
	@Bean
	public ContratItemProcessor processor()
	{
		return new ContratItemProcessor();
	}
	
	/*@Bean
	public JdbcBatchItemWriter<Contrat> writer()
	{
		JdbcBatchItemWriter<Contrat> writer = new JdbcBatchItemWriter<Contrat>();
		//writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Contrat>());
		writer.setSql("insert into public.contrat (id_contrat,addresse,date_debut,nom,prenom) values (:id_contrat,:addresse,:date_debut,:nom,:prenom)");
		writer.setDataSource(dataSource);
		return writer;
		
	}*/
	
	@Bean
	public Step step1()
	{
		return stepBuilderFactory.get("step1").<Contrat,Contrat>chunk(6)
				.reader( reader())
				.processor(processor())
				.writer(new Writer(contratDAO))
				.build();
	}
	@Bean
	public Job importUserJob()
	{
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
		
	}
	
	
	
	
}
