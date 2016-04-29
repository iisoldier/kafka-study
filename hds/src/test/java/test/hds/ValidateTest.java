package test.hds;

import javax.validation.ValidationException;
import com.hds.api.ccs.vo.CCSDemoRequestVo;
import com.hds.api.common.util.VlidationUtil;

 
/** 
 * @ClassName: 测试类
 * @Description: TODO 
 * @author lenvo
 * @date 2016-4-13 上午11:15:15  
 */
public class ValidateTest {
 
    public static void main(String [] args ){
    	CCSDemoRequestVo ccsrequestVo = new CCSDemoRequestVo();
    	
    	ccsrequestVo.setSysid("ectdata_hdssysid_ccs");
    	ccsrequestVo.setSpkpk1(1);
    	ccsrequestVo.setSpkpk2(2);
    	ccsrequestVo.setEpkpk1(1);
    	ccsrequestVo.setEpkpk2(2);
    	ccsrequestVo.setDirection("FORWARD");
    	ccsrequestVo.setLimit(200);
     	
    	//测试
    	ccsrequestVo.setEndday("abcd123444");
    	ccsrequestVo.setStartday("2016-07-06");
    	
        try {
            VlidationUtil.validate(ccsrequestVo);
            System.out.println("校验成功");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
 
}