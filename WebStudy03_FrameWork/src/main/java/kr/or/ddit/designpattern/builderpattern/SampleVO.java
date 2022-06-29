package kr.or.ddit.designpattern.builderpattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
//@NoArgsConstructor
public class SampleVO {

//	public SampleVO(String prop1) {
//		this.prop1 = prop1;
//	}
//	
//	public SampleVO(String prop1, String prop2) {
//		this.prop1 = prop1;
//		this.prop2 = prop2;
//	}
//	
//	public SampleVO(String prop1, String prop3, String prop2) {
//		this.prop1 = prop1;
//		this.prop3 = prop3;
//		this.prop2 = prop2;
//	}


//	// @NoArgsConstructor 사용 x, 지시자는 private
//	private SampleVO(String prop1, String prop2, String prop3) {
//		super();
//		this.prop1 = prop1;
//		this.prop2 = prop2;
//		this.prop3 = prop3;
//	}

	private String prop1;
	private String prop2;
	private String prop3;
	
//	public static SampleVOBuilder builder() {
//		return new SampleVOBuilder();
//	}
	
//	public static class SampleVOBuilder {
//		private String prop1;
//		private String prop2;
//		private String prop3;
//		public SampleVOBuilder prop1(String prop1) {
//			this.prop1 = prop1;
//			return this;
//		}
//		public SampleVOBuilder prop2(String prop2) {
//			this.prop2 = prop2;
//			return this;
//		}
//		public SampleVOBuilder prop3(String prop3) {
//			this.prop3 = prop3;
//			return this;
//		}
//		
//		public SampleVO build() {
//			return new SampleVO(prop1, prop2, prop3);
//		}
//	}
}
