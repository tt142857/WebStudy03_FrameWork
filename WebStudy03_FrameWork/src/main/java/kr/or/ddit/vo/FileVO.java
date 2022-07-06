package kr.or.ddit.vo;

import java.io.File;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileVO {
	private String placeholder;
	private File fileSystemResource;
	private File webResource;
}
