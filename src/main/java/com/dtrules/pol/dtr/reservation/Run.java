package com.dtrules.pol.dtr.reservation;

import java.io.PrintStream;
import com.dtrules.entity.IREntity;
import com.dtrules.infrastructure.RulesException;
import com.dtrules.interpreter.IRObject;
import com.dtrules.interpreter.RArray;
import com.dtrules.interpreter.operators.ROperator;
import com.dtrules.mapping.DataMap;
import com.dtrules.mapping.Mapping;
import com.dtrules.session.DTState;
import com.dtrules.session.IRSession;
import com.dtrules.testsupport.ATestHarness;
import com.dtrules.testsupport.ITestHarness;
import com.dtrules.xmlparser.XMLPrinter;

public class Run extends ATestHarness {
	
    private static final String ROOT_DECISION_TABLE_NAME_TO_CALL_FIRST = "TicketBooking";
	public static String PATH    = Compiler.PATH;
	private static final String RULE_SET = Compiler.RULE_SET;
	private static final String RULES_CONFIG = Compiler.RULES_CONFIG;
		
    public boolean  Trace()                   { return true;                            }
    public boolean  Console()                 { return true;                            }
    public String   getPath()                 { return PATH;							}
    public String   getRulesDirectoryPath()   { return getPath()+"xml/";                }
    public String   getRuleSetName()          { return RULE_SET;                	}
    public String   getDecisionTableName()    { return ROOT_DECISION_TABLE_NAME_TO_CALL_FIRST;           	}
    public String   getRulesDirectoryFile()   { return RULES_CONFIG;                   }             

    public static void main(String[] args) { 
        ITestHarness t = new Run();
        System.out.println("begin");
        t.runTests();
        System.out.println("end");
    }
    
    public void printReport(int runNumber, IRSession session, PrintStream _out) throws RulesException {
        XMLPrinter xout = new XMLPrinter(_out);
        DTState sessionState = session.getState();
        
        System.out.println("print results");
         
        IRObject ticketObject = sessionState.find("ticket");
       	IREntity ticketEntity = ticketObject.rEntityValue();       	
       	RArray passengers = sessionState.find("passengers").rArrayValue();
       	
       	xout.opentag("ticket");
	       	xout.printdata("passenger_count",ticketEntity.get("passenger_cnt").intValue());
	       	xout.printdata("alloted_seat",ticketEntity.get("alloted_seat").intValue());
	       	xout.printdata("has_adult",ticketEntity.get("adult_flag").booleanValue());
	       	xout.printdata("PRN",ticketEntity.get("PRN").stringValue());
	       	xout.printdata("ticket_price",ticketEntity.get("ticket_price").doubleValue());
	       	xout.printdata("total_ticket_price",ticketEntity.get("total_ticket_price").doubleValue());
	       	xout.printdata("total_amount",ticketEntity.get("total_amount").doubleValue());
	       	xout.printdata("booking_reason",ticketEntity.get("booking_reason").stringValue());
	       	xout.opentag("passenger_deatils");
	       	for(IRObject passengerObject :passengers){
				IREntity passenger = passengerObject.rEntityValue();
				xout.opentag("passenger");
				prt(xout,passenger,"full_name");
				prt(xout,passenger,"gender");
				prt(xout,passenger,"age");
				prt(xout,passenger,"price");
				xout.closetag();
			}
        xout.close();
    }
 
    private void prt(XMLPrinter xout, IREntity entity, String attrib){
        IRObject value = entity.get(attrib);
        xout.printdata(attrib,value.stringValue());
    }
  
}    
	    
