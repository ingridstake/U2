import React, {useEffect, useState } from "react";
import { Button, ListGroup, ListGroupItem } from 'react-bootstrap';
import '../styles/searchBar.css';
import SearchBar from './SearchBar'
import axios from 'axios';

import {event} from "./Models";
import { SearchItem } from "./SearchItem";

/*
import '../styles/showEvents.css';
import '../styles/showEventCat.css';
import {event} from "./Models";
import { EventCard } from "./EventCard";

/**
 * Creates a list of event cards.
 * @param events is the list of the events
 * @returns a list of event cards
 */
/*
 export function EventCards(events: event[]) : JSX.Element[] {
    const res = events.map((e: event) => {
        return EventCard(e)
    })
    return res
}*/

export function SearchList(events: event[]) : JSX.Element {
    const res = events?.map((e: event) => {

        return SearchItem(e)
    })
    return <div>{res}</div>
}


type searchResult = {
    events: event[];
}

export default class SearchResultList extends React.Component<searchResult, searchResult> implements JSX.Element {
    type: any;
    key: React.Key | null = null;
    state: searchResult = {events: []}

    updateSearchResult(newEvents: event[]) {
        this.setState((state => ({
            events : newEvents
        })));
    }

    render() {
        const res = this.state.events?.map((e: event) => {
            return SearchItem(e)
        } );
        
        return (
            <div>
                res
            </div>
        );
    }
}




/*
export const SearchResultList = (events: event[])  => {
    return class extends React.Component implements JSX.Element {
        type: any;
        key: React.Key | null;

        constructor(){
            super();
            this.key
        }

        
        readonly res = events?.map((e: event) => {
            return SearchItem(e)
        })
        render(){
            return (
                <div>
                   res
                </div>
            )
        }
    }
}*/