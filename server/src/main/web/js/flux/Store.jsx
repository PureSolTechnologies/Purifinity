import { createStore, combineReducers } from 'redux';

import { LOGIN_ACTION, LOGOUT_ACTION } from './LoginActions';
import { YEAR_CHANGE_ACTION, MONTH_CHANGE_ACTION, DAY_CHANGE_ACTION } from './CalendarActions';

function loginReducer( state = null, action ) {
    switch ( action.type ) {
        case LOGIN_ACTION:
            return action.user;
        case LOGOUT_ACTION:
            return null;
        default:
            return state;
    }
}

function calendarReducer( state = null, action ) {
    if (state == null) {
        var today = new Date();
        state = { year: today.getUTCFullYear(), month: today.getMonth() + 1, day: today.getDate() }
    }
    switch ( action.type ) {
        case YEAR_CHANGE_ACTION:
            state.year = action.year;
            return state;
        case MONTH_CHANGE_ACTION:
            state.month = action.month;
            return state;
        case DAY_CHANGE_ACTION:
            state.day = action.day;
            return state;
        default:
            return state;
    }
}

const store = createStore( combineReducers(
    { login: loginReducer, calendar: calendarReducer }) );

export default store;
