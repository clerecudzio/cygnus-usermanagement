package com.cygnus.sys.umgt

import org.springframework.dao.DataIntegrityViolationException

class AuthoritiesController {
	def universalSearchService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [authoritiesInstanceList: Authorities.list(params), authoritiesInstanceTotal: Authorities.count()]
    }

    def create() {
        [authoritiesInstance: new Authorities(params)]
    }

    def save() {
        def authoritiesInstance = new Authorities(params)
        if (!authoritiesInstance.save(flush: true)) {
            render(view: "create", model: [authoritiesInstance: authoritiesInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'authorities.label', default: 'Authorities'), authoritiesInstance.id])
        redirect(action: "show", id: authoritiesInstance.id)
    }

    def show() {
        def authoritiesInstance = Authorities.get(params.id)
        if (!authoritiesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'authorities.label', default: 'Authorities'), params.id])
            redirect(action: "list")
            return
        }

        [authoritiesInstance: authoritiesInstance]
    }

    def edit() {
        def authoritiesInstance = Authorities.get(params.id)
        if (!authoritiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'authorities.label', default: 'Authorities'), params.id])
            redirect(action: "list")
            return
        }

        [authoritiesInstance: authoritiesInstance]
    }

    def update() {
        def authoritiesInstance = Authorities.get(params.id)
        if (!authoritiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'authorities.label', default: 'Authorities'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (authoritiesInstance.version > version) {
                authoritiesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'authorities.label', default: 'Authorities')] as Object[],
                          "Another user has updated this Authorities while you were editing")
                render(view: "edit", model: [authoritiesInstance: authoritiesInstance])
                return
            }
        }

        authoritiesInstance.properties = params

        if (!authoritiesInstance.save(flush: true)) {
            render(view: "edit", model: [authoritiesInstance: authoritiesInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'authorities.label', default: 'Authorities'), authoritiesInstance.id])
        redirect(action: "show", id: authoritiesInstance.id)
    }

    def delete() {
        def authoritiesInstance = Authorities.get(params.id)
        if (!authoritiesInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'authorities.label', default: 'Authorities'), params.id])
            redirect(action: "list")
            return
        }

        try {
            authoritiesInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'authorities.label', default: 'Authorities'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'authorities.label', default: 'Authorities'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cygnusFilteredSearch(){
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		log.info "params = "+params.toString();
		def result = universalSearchService.generateResult(params)
		render (view:"list",model: [authoritiesInstanceList: result.resultList, authoritiesInstanceTotal: result.resultListSize])
	}
}
