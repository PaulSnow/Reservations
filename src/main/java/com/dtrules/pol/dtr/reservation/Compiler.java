package com.dtrules.pol.dtr.reservation;

import excel.util.Excel2XML;

public class Compiler {

	public static String PATH = System.getProperty("user.dir") + "/";
	public static final String RULE_SET = "reservation_Ruleset";
	public static final String RULES_CONFIG = "reservation_DTRules.xml";
	private static final String REPOSITORY = "repository";

	public static void main(String args[]) throws Exception {
		try {
			Excel2XML.compile(PATH, RULES_CONFIG, RULE_SET, REPOSITORY, new String[] { "request", "output" });
		} catch (Exception ex) {
			System.out.println("Failed to convert the Excel files");
			ex.printStackTrace();
			throw ex;
		}
	}
}
