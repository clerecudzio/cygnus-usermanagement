import com.cygnus.sys.umgt.Authorities
import com.cygnus.sys.umgt.Person
import com.cygnus.sys.umgt.PersonAuthorities
import com.cygnus.sys.umgt.RequestMap




class CygnusUmgtBootStrap {
	
	
	
	def init = { servletContext ->
		
		environments{
			development{
			
				
			}
			test{
				
			}
			production{
				
			}
		}
		

	}
	
	def destroy = {
	}

}