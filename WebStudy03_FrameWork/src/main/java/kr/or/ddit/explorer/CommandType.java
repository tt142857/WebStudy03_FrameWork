package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public enum CommandType {
	COPY((srcFile, destFolder)->{
		Path in = Paths.get(srcFile.getPath());
		Path target = Paths.get(destFolder.getPath(), srcFile.getName());
		Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
		return true;
	}), 
	DELETE((srcFile, destFolder)->{
		return srcFile.delete();
	}),
	MOVE((srcFile, destFolder)->{
		boolean result = false;
		if(COPY.process(srcFile, destFolder)) {
			result = DELETE.process(srcFile, null);
		}
		return result;
	}); 
	private interface CommandProcessor{
		public boolean process(File srcFile, File destFolder) throws IOException;
	}
	private CommandProcessor processor;
	private CommandType(CommandProcessor processor) {
		this.processor = processor;
	}
	public boolean process(File src, File dest) throws IOException {
		return processor.process(src, dest);
	}
}
