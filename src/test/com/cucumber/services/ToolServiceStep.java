package com.cucumber.services;

import com.db.FlatEntityDao;
import com.db.FlatUserEntity;
import com.service.ToolService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class ToolServiceStep {
    private ToolService toolService = new ToolService();
    private FlatEntityDao flatEntityDao = new FlatEntityDao();
    List<FlatUserEntity> flatUserEntityList;
    private String userPath;
    private String cityPath;
    private String countryPath;

    @Given("^user path (.*) city path (.*) and country path (.*)$")
    public void correctPathToFile(String userPath, String cityPath, String countryPath) {
        this.userPath = userPath;
        this.cityPath = cityPath;
        this.countryPath = countryPath;
    }

    @When("^toolService write data 4 FlatUserEntity in db$")
    public void starWorkService() {
        toolService.importData(userPath, cityPath, countryPath);
        flatUserEntityList = flatEntityDao.printAllFlatUsers();
    }

    @Then("^toolService work correct and add new (\\\\d+) entity$")
    public void ToolServiceWorkDone(int countNewEntity) {
        assertThat(countNewEntity, equalTo(flatUserEntityList.size()));
    }
}
