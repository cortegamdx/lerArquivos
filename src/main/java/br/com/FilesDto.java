package br.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FilesDto {

	private String fileName;
	private String contentFile;
	private boolean lido = false;
	


	public FilesDto(File file) {
		this.fileName = file.getName();
		this.contentFile = "";
	}

	

	public boolean isLido() {
		return lido;
	}


	public void setLido(boolean lido) {
		this.lido = lido;
	}

	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentFile() {
		return contentFile;
	}
	public void setContentFile(String contentFile) {
		this.contentFile = contentFile;
	}


	public static String readFile(File file)  {
		String content = "";
		try {
			if(file.getName().endsWith("txt")) {

				FileReader reader = new FileReader(file);
				BufferedReader buffer = new BufferedReader(reader);
				
				
				String line= "";
				
				while((line = buffer.readLine())!= null) {
					content += line + "\n";
				
				}

				reader.close();
				buffer.close();
			}else {
				content = "Diretorio";
			}	

		}catch (Exception e) {
			e.printStackTrace();
		}	

		return content;
	}

}
