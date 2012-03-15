package com.cygnus.sys.umgt

class Authorities {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
