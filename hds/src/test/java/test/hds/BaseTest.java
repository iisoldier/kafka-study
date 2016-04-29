/** 
* @ClassName:test.hds
* @Description:
* @author zengli
* @date 创建时间：2016年4月19日 上午10:57:21
*/
package test.hds;

import com.hds.api.exception.HDSServiceException;

/** 
 * @ClassName:test.hds.BaseTest
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月19日 上午10:57:21
 */
public class BaseTest {

	//日期差
	public static void main(String args[]) throws HDSServiceException{
		
		long currentTimeMilles = System.currentTimeMillis() / 1000;
		System.out.println(currentTimeMilles);
	}

	
}
