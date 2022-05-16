import {useEffect, useState } from "react";
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
    return <>{res}</>
}