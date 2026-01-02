package com.automation.selenium.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name = "inputData")
    public static Object[][] getInputData() {
        return ExcelUtils.getTestData(
                "src/test/resources/testdata/inputData.xlsx",
                "Sheet1"
        );
    }

    @DataProvider(name = "jsonFormData")
    public static Object[][] getFormDataFromJson() {
        JsonNode dataNode = JsonUtils.readJson("src/test/resources/testdata/formData.json").get("formData");
        Object[][] data = new Object[dataNode.size()][1];
        for (int i = 0; i < dataNode.size(); i++) {
            data[i][0] = dataNode.get(i);
        }
        return data;
    }


}
