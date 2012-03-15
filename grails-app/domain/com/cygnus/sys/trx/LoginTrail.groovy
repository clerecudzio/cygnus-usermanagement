package com.cygnus.sys.trx

import com.cygnus.sys.umgt.Person
import java.sql.Timestamp

/**
 * Object for storing login history
 * @author haris
 *
 */
class LoginTrail {

	Person person
	Timestamp actionStamp
	byte action //0 Login 1 Logout
	String ipAddress
	
    static constraints = {
		person nullable:false
		actionStamp nullable:false
		ipAddress nullable:false	
    }
	
	static mapping = {
		table 'SYS_TRX_LOGINTRAIL'
	}
	
	def beforeInsert(){
		actionStamp = new Timestamp()	
	}
}
