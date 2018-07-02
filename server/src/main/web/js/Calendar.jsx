import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Menu from './Menu';

import store from './flux/Store';

import DateSelector from './components/calendar/DateSelector';
import DayCalendar from './pages/calendar/DayCalendar';
import WeekCalendar from './pages/calendar/WeekCalendar';
import MonthCalendar from './pages/calendar/MonthCalendar';
import YearCalendar from './pages/calendar/YearCalendar';

export default class Calendar extends React.Component {

    constructor( props ) {
        super( props );
    }

    static getCurrentYear() {
        var calendar = store.getState().calendar;
        return "/calendar/year/" + calendar.year;
    }


    static getCurrentMonth() {
        var calendar = store.getState().calendar;
        return "/calendar/month/" + calendar.year + "/" + calendar.month;
    }

    static getCurrentWeek() {
        var calendar = store.getState().calendar;
        return "/calendar/week/" + calendar.year + "/" + calendar.year;
    }

    static getCurrentDay() {
        var calendar = store.getState().calendar;
        return "/calendar/day/" + calendar.year + "/" + calendar.month + "/" + calendar.day;
    }


    render() {
        return (
            <div>
                <Menu />
                <div className="row">
                    <div className="col-md-3">
                        <DateSelector />
                    </div>
                    <div className="col-md-9">
                        <Switch>
                            <Redirect exact from="/calendar/year" to={Calendar.getCurrentYear()} />
                            <Redirect exact from="/calendar/month" to={Calendar.getCurrentMonth()} />
                            <Redirect exact from="/calendar/week" to={Calendar.getCurrentWeek()} />
                            <Redirect exact from="/calendar/day" to={Calendar.getCurrentDay()} />
                            <Route path="/calendar/year/:year" component={YearCalendar} />
                            <Route path="/calendar/month/:year/:month" component={MonthCalendar} />
                            <Route path="/calendar/week/:year/:week" component={WeekCalendar} />
                            <Route path="/calendar/day/:year/:month/:day" component={DayCalendar} />
                        </Switch>
                    </div>
                </div>
            </div>
        );
    }
}
