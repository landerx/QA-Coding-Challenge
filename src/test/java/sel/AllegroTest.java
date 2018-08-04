package sel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import sel.base.TestBase;
import sel.pages.impl.DearUserPopUpPage;

import static properties.AppProperties.SECOND_TASK_BASE_URL;

@Slf4j
public class AllegroTest extends TestBase {

    private DearUserPopUpPage dearUserPopupPage;

    @Before
    public void setup() {
        webDriver.navigate().to(SECOND_TASK_BASE_URL.getValue());
        dearUserPopupPage = new DearUserPopUpPage(webDriver);
    }

    @Test
    public void asUserFindExternalHardDrivesAndPortableDiscsBetween500And1000GbSortedDescendByPrice() {
        //given
        int minCapacityInGb = 500;
        int maxCapacityInGb = 1000;

        //when
        dearUserPopupPage.clickOnXButton()
                .clickOnCategoriesLink()
                .clickOnElectronicsLink()
                .clickOnComputersLink()
                .clickOnPortableDiscsAndMemoryLink()
                .clickOnExternalAndPortableDiscsLink()

                //then
                .assertThatExternalAndPortableDiscsPageIsDisplayed()

                //when
                .typeIntoMinCapacity(minCapacityInGb)
                .typeIntoMaxCapacity(maxCapacityInGb)
                .pickSortDescendingByPriceFromSortDropdown()

                //then
                .assertThatSortDescendingByPriceIsSelected()
                .assertThatPromotedOffersAreBetweenGbValues(minCapacityInGb, maxCapacityInGb)
                .assertThatCommonOffersAreBetweenGbValues(minCapacityInGb, maxCapacityInGb)
                .assertThatPromotedOffersAreSortedDescendByPrice()
                .assertThatCommonOffersAreSortedDescendByPrice();
    }

}
