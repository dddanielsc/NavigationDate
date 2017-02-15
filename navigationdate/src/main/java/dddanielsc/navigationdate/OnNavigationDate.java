package dddanielsc.navigationdate;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by dddanielsc on 03/02/17.
 */

public interface OnNavigationDate {

    void onDateChosen (NavigationDate navigationDate , DateTime begin , DateTime end);
}
