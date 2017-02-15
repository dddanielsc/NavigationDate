package dddanielsc.navigationdate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Created by dddanielsc on 02/02/17.
 */

public class NavigationDate extends LinearLayout {

    public static final int INTERVAL_DAY = 1;
    public static final int INTERVAL_WEEK = 2;
    public static final int INTERVAL_MONTH = 3;
    public static final int INTERVAL_YEAR = 4;

    private Context mContext;

    private TextView mLabel;
    private ImageView iconBack;
    private ImageView iconNext;

    private OnNavigationDate onNavigationDate;

    private DateTime mDateCurrent;
    private Interval mInterval;
    private int mIntervalType;


    /*constructors*/
    public NavigationDate(Context context) {
        super(context);

        initialize( context );
    }

    public NavigationDate(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize( context );
    }



    /*private methods*/
    private void initialize(Context context){

        this.mContext = context;

        View view = inflate( context , R.layout.navigation_layout , this);

        iconBack = (ImageView) view.findViewById(R.id.i_action_back);
        iconNext = (ImageView) view.findViewById(R.id.i_action_forward);

        mLabel = (TextView) view.findViewById(R.id.label_data);

        mIntervalType = INTERVAL_DAY;
        mDateCurrent = new DateTime();
        mInterval = new Interval( mDateCurrent , mDateCurrent);


        mLabel.setText( mDateCurrent.toString( Utils.formatterJodaTimeBR() ));

        initEvents(context);

    }

    private void initEvents(Context context){

        iconBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onNavigationDate == null) return;

                minusByIntervalType( 1 );

                onChangeLabelByIntervalType( getmIntervalType() );

                onNavigationDate.onDateChosen( NavigationDate.this , getNewInterval().getStart() , getNewInterval().getEnd());
            }
        });



        iconNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onNavigationDate == null) return;

                plusByIntervalType( 1 );

                onChangeLabelByIntervalType( getmIntervalType() );

                onNavigationDate.onDateChosen( NavigationDate.this , getNewInterval().getStart() , getNewInterval().getEnd());
            }
        });

    }

    private int getmIntervalType (){
        return this.mIntervalType;
    }

    private Interval getNewInterval(){


        switch ( mIntervalType ){

            case INTERVAL_DAY:

                this.mInterval = new Interval( mDateCurrent , mDateCurrent);

                return mInterval;

            case INTERVAL_WEEK:

                this.mInterval = new Interval( mDateCurrent.dayOfWeek().withMinimumValue() ,mDateCurrent.dayOfWeek().withMaximumValue() );

                return mInterval;

            case INTERVAL_MONTH:

                this.mInterval = new Interval( mDateCurrent.dayOfMonth().withMinimumValue() ,mDateCurrent.dayOfMonth().withMaximumValue() );

                return mInterval;

            case INTERVAL_YEAR:


                this.mInterval = new Interval( mDateCurrent.dayOfYear().withMinimumValue() ,mDateCurrent.dayOfYear().withMaximumValue() );

                return mInterval;

            default:

                this.mInterval = new Interval( mDateCurrent , mDateCurrent);

                return mInterval;
        }

    }

    private void plusByIntervalType( int plus){

        switch ( mIntervalType ){

            case INTERVAL_DAY:

                mDateCurrent = mDateCurrent.plusDays( plus );

                break;

            case INTERVAL_WEEK:

                mDateCurrent = mDateCurrent.plusWeeks( plus );

                break;

            case INTERVAL_MONTH:

                mDateCurrent = mDateCurrent.plusMonths( plus );

                break;

            case INTERVAL_YEAR:

                mDateCurrent = mDateCurrent.plusYears( plus );


                break;
        }

    }

    private void minusByIntervalType( int minus){

        switch ( mIntervalType ){

            case INTERVAL_DAY:

                mDateCurrent = mDateCurrent.minusDays(minus);

                break;

            case INTERVAL_WEEK:

                mDateCurrent = mDateCurrent.minusWeeks(minus);

                break;

            case INTERVAL_MONTH:

                mDateCurrent = mDateCurrent.minusMonths(minus);

                break;

            case INTERVAL_YEAR:

                mDateCurrent = mDateCurrent.minusYears(minus);

                break;
        }

    }

    private void onChangeLabelByIntervalType(int newIntervalType){

        switch (newIntervalType){

            case INTERVAL_DAY:

                String date = mDateCurrent.toString( Utils.formatterJodaTimeBR() );

                mLabel.setText(date);

                break;

            case INTERVAL_WEEK:

                mLabel.setText( Utils.formatterJodaTimeWeekBR( mDateCurrent ));

                break;

            case INTERVAL_MONTH:

                String dateMonth = mDateCurrent.toString( Utils.formatterJodaTimeMonthYearBR() );

                mLabel.setText( dateMonth );

                break;

            case INTERVAL_YEAR:

                String dateYear = mDateCurrent.toString( Utils.formatterJodaTimeYear() );

                mLabel.setText( dateYear );

                break;


        }

    }


    /*api methods*/

    public void setOnNavigationDate (OnNavigationDate navigationDate){
        this.onNavigationDate = navigationDate;
    }

    public void setTextLabel (String label){

        this.mLabel.setText( label );

    }

    public void initDate(DateTime date){
        this.mDateCurrent = date;
    }

    public void setDateCurrent(DateTime mDateCurrent) {
        this.mDateCurrent = mDateCurrent;

        onChangeLabelByIntervalType( getmIntervalType() );

        onNavigationDate.onDateChosen( NavigationDate.this , getNewInterval().getStart() , getNewInterval().getEnd());
    }

    public void setIntervalType (int intervalType) {
        this.mIntervalType = intervalType;

        onChangeLabelByIntervalType(mIntervalType);

        onNavigationDate.onDateChosen( NavigationDate.this , getNewInterval().getStart() , getNewInterval().getEnd());
    }

    public DateTime getDateCurrent (){
        return mDateCurrent;
    }



}
