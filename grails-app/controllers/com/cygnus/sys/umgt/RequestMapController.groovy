package com.cygnus.sys.umgt

import org.springframework.dao.DataIntegrityViolationException

class RequestMapController {
	def universalSearchService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [requestMapInstanceList: RequestMap.list(params), requestMapInstanceTotal: RequestMap.count()]
    }

    def create() {
        [requestMapInstance: new RequestMap(params)]
    }

    def save() {
        def requestMapInstance = new RequestMap(params)
        if (!requestMapInstance.save(flush: true)) {
            render(view: "create", model: [requestMapInstance: requestMapInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), requestMapInstance.id])
        redirect(action: "show", id: requestMapInstance.id)
    }

    def show() {
        def requestMapInstance = RequestMap.get(params.id)
        if (!requestMapInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])
            redirect(action: "list")
            return
        }

        [requestMapInstance: requestMapInstance]
    }

    def edit() {
        def requestMapInstance = RequestMap.get(params.id)
        if (!requestMapInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])
            redirect(action: "list")
            return
        }

        [requestMapInstance: requestMapInstance]
    }

    def update() {
        def requestMapInstance = RequestMap.get(params.id)
        if (!requestMapInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (requestMapInstance.version > version) {
                requestMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'requestMap.label', default: 'RequestMap')] as Object[],
                          "Another user has updated this RequestMap while you were editing")
                render(view: "edit", model: [requestMapInstance: requestMapInstance])
                return
            }
        }

        requestMapInstance.properties = params

        if (!requestMapInstance.save(flush: true)) {
            render(view: "edit", model: [requestMapInstance: requestMapInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), requestMapInstance.id])
        redirect(action: "show", id: requestMapInstance.id)
    }

    def delete() {
        def requestMapInstance = RequestMap.get(params.id)
        if (!requestMapInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])
            redirect(action: "list")
            return
        }

        try {
            requestMapInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	
	def cygnusFilteredSearch(){
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		log.info "params = "+params.toString();
		def result = universalSearchService.generateResult(params)
		render (view:"list",model: [requestMapInstanceList: result.resultList, requestMapInstanceTotal: result.resultListSize])
	}
}
