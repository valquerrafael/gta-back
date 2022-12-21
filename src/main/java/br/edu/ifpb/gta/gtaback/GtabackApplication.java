package br.edu.ifpb.gta.gtaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GtabackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtabackApplication.class, args);
		// se institution for deletada, deleta seus teachers e students
		// se teacher for deletado, deleta suas trilhas e atualiza institution
		// se student for deletado, atualiza suas trilhas e institution
		// se trail for deletada, atualiza seus students e teachers
	}

}
