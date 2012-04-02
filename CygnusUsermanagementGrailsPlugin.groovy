import org.codehaus.groovy.grails.plugins.springsecurity.*

import com.cygnus.sys.umgt.RequestMap
import grails.plugins.springsecurity.*

class CygnusUsermanagementGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
	def loadAfter = ['cygnusTransactionmanager']

    // TODO Fill in these fields
    def title = "Cygnus User Management Plugin" // Headline display name of the plugin
    def author = "Haris Ibrahim"
    def authorEmail = "clerecudzio@gmail.com"
    def description = ''' Basic User Management for Cygnus Framework. 
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/cygnus-usermanagement"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.grails-plugins.codehaus.org/browse/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
	
	
		
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
		
		// Added by the Spring Security Core plugin:
		/**
		 * 
		 NOT WORKING !!!! should find another workaround
		 Temporarily, config was stored in Config.groovy of main project
		SpringSecurityUtils.securityConfig.userLookup.userDomainClassName = 'com.cygnus.sys.umgt.Person'
		SpringSecurityUtils.securityConfig.userLookup.authorityJoinClassName = 'com.cygnus.sys.umgt.PersonAuthorities'
		SpringSecurityUtils.securityConfig.authority.className = 'com.cygnus.sys.umgt.Authorities'
		SpringSecurityUtils.securityConfig.requestMap.className = 'com.cygnus.sys.umgt.RequestMap'
		SpringSecurityUtils.securityConfig.securityConfigType = SecurityConfigType.Requestmap
		*/
		
		
	}

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
