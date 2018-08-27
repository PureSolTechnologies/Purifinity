import { createStore, combineReducers } from 'redux';

function dummyReducer( state = null, action ) {
    return state;
}

const store = createStore( combineReducers(
    { dummy: dummyReducer } ) );

export default store;
