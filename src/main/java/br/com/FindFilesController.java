package br.com;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FindFilesController {

	private static File[] files;
	private static List<FilesDto> filesReaded =  new ArrayList<>();

	@GetMapping("/path/{path}")
	public List<FilesDto> listFiles(@PathVariable(value="path") String path){
		files = new File("C:\\"+path).listFiles();

		List<FilesDto> arquivos = Arrays.asList(files).stream().map(file -> (new FilesDto(file))).collect(Collectors.toList()); 

		return arquivos;

	}


	@GetMapping("/read")
	public List<FilesDto> lerArquivos(){


		List<File> arquivos = Arrays.asList(files);


		if(filesReaded.size() == 0) {
			for(int i = 0; i < arquivos.size(); i++) {
				FilesDto dto = new FilesDto(arquivos.get(i));
				dto.setContentFile(FilesDto.readFile(arquivos.get(i)));
				dto.setLido(true);
				filesReaded.add(dto);
			}

			return filesReaded;
		}
		
		
		List<String> nomesArquivosLidos = new ArrayList<>();
		
		for(FilesDto dto : filesReaded) {
			nomesArquivosLidos.add(dto.getFileName());
		}
		
		
		for (File file : arquivos) {
			FilesDto novoArquivo = new FilesDto(file);
			
			if(nomesArquivosLidos.indexOf(novoArquivo.getFileName())!= -1) {
				break;
			}else {
				filesReaded.add(novoArquivo);
			}
			
		}
		
		


	return filesReaded;
}


@GetMapping("/lidos")
public List<FilesDto> arquivosLidos(){
	return filesReaded;
}

}
