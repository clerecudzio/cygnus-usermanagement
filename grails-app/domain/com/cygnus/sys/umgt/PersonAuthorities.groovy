package com.cygnus.sys.umgt

import org.apache.commons.lang.builder.HashCodeBuilder

class PersonAuthorities implements Serializable {

	Person person
	Authorities authorities

	boolean equals(other) {
		if (!(other instanceof PersonAuthorities)) {
			return false
		}

		other.person?.id == person?.id &&
			other.authorities?.id == authorities?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (person) builder.append(person.id)
		if (authorities) builder.append(authorities.id)
		builder.toHashCode()
	}

	static PersonAuthorities get(long personId, long authoritiesId) {
		find 'from PersonAuthorities where person.id=:personId and authorities.id=:authoritiesId',
			[personId: personId, authoritiesId: authoritiesId]
	}

	static PersonAuthorities create(Person person, Authorities authorities, boolean flush = false) {
		new PersonAuthorities(person: person, authorities: authorities).save(flush: flush, insert: true)
	}

	static boolean remove(Person person, Authorities authorities, boolean flush = false) {
		PersonAuthorities instance = PersonAuthorities.findByPersonAndAuthorities(person, authorities)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(Person person) {
		executeUpdate 'DELETE FROM PersonAuthorities WHERE person=:person', [person: person]
	}

	static void removeAll(Authorities authorities) {
		executeUpdate 'DELETE FROM PersonAuthorities WHERE authorities=:authorities', [authorities: authorities]
	}

	static mapping = {
		id composite: ['authorities', 'person']
		version false
	}
}
