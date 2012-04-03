package com.cygnus.sys.umgt

import org.springframework.dao.DataIntegrityViolationException

class PersonAuthoritiesController {
	def universalSearchService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [personAuthoritiesInstanceList: PersonAuthorities.list(params), personAuthoritiesInstanceTotal: PersonAuthorities.count()]
    }

    def create() {
        [personAuthoritiesInstance: new PersonAuthorities(params)]
    }

    def save() {
        def personAuthoritiesInstance = new PersonAuthorities(params)
        if (!personAuthoritiesInstance.save(flush: true)) {
            render(view: "create", model: [personAuthoritiesInstance: personAuthoritiesInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), personAuthoritiesInstance.id])
        redirect(action: "show", id: personAuthoritiesInstance.id)
    }

    def show() {
        def personAuthoritiesInstance = PersonAuthorities.get(params.id)
        if (!personAuthoritiesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), params.id])
            redirect(action: "list")
            return
        }

        [personAuthoritiesInstance: personAuthoritiesInstance]
    }

    def edit() {
        def personAuthoritiesInstance = PersonAuthorities.get(params.id)
        if (!personAuthoritiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), params.id])
            redirect(action: "list")
            return
        }

        [personAuthoritiesInstance: personAuthoritiesInstance]
    }

    def update() {
        def personAuthoritiesInstance = PersonAuthorities.get(params.id)
        if (!personAuthoritiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (personAuthoritiesInstance.version > version) {
                personAuthoritiesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'personAuthorities.label', default: 'PersonAuthorities')] as Object[],
                          "Another user has updated this PersonAuthorities while you were editing")
                render(view: "edit", model: [personAuthoritiesInstance: personAuthoritiesInstance])
                return
            }
        }

        personAuthoritiesInstance.properties = params

        if (!personAuthoritiesInstance.save(flush: true)) {
            render(view: "edit", model: [personAuthoritiesInstance: personAuthoritiesInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), personAuthoritiesInstance.id])
        redirect(action: "show", id: personAuthoritiesInstance.id)
    }

    def delete() {
        def personAuthoritiesInstance = PersonAuthorities.get(params.id)
        if (!personAuthoritiesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), params.id])
            redirect(action: "list")
            return
        }

        try {
            personAuthoritiesInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'personAuthorities.label', default: 'PersonAuthorities'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cygnusFilteredSearch(){
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		log.info "params = "+params.toString();
		def result = universalSearchService.generateResult(params)
		render (view:"list",model: [personAuthoritiesInstanceList: result.resultList, personAuthoritiesInstanceTotal: result.resultListSize])
	}
}
