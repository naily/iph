/**
 * 描述：
 */
package cn.fam1452.service;

import org.apache.log4j.Logger;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;

/**
 * Class Base
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 20, 2012 10:37:00 AM $
 * @param <T>
 */

public abstract class Base {

	protected Logger log = Logger.getLogger(this.getClass()) ;  
	
	@Inject("refer:dao")
	public NutDao dao ;
	
}
