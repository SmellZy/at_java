package task_24;

import com.fasterxml.jackson.databind.ObjectMapper;
import itstep.task_14.AllureListener;
import itstep.task_24.bo.CalcBO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task_17.TrelloBO;
import task_17.model.createBoardInOrg_Models.CreateBoardInOrgRequest;
import task_17.model.createBoardInOrg_Models.CreateBoardInOrgResponse;
import task_17.model.createBoardInOrg_Models.TrelloBoardInOrg;
import task_17.model.createOrg_Model.CreateOrgRequest;
import task_17.model.createOrg_Model.CreateOrgResponse;
import task_17.model.createOrg_Model.DeleteRequest;
import task_17.model.createOrg_Model.TrelloOrg;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Properties;
import java.util.UUID;

@Listeners({AllureListener.class})
public class Task_24_test {



    @Test
    void calcTest() throws FindFailed {
        CalcBO calcBO= new CalcBO();

        calcBO.openCalcApp()
                .tipe1_p_5_p()
                .checkSum6();

    }
}
