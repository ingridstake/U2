import React from "react";
import '../styles/searchBar.css';
import {event} from "./Models";
import { SearchItem } from "./SearchItem";

/**
 * Creates a list of event cards.
 * @param events is the list of the events
 * @returns a list of event cards
 */

type searchResult = {
    events: event[];
}

export default class SearchResultList extends React.Component<searchResult, searchResult> implements JSX.Element {
    type: any;
    key: React.Key | null = null;
    state: searchResult = {events: []}

    constructor(props: searchResult){
        super(props);
        this.state = {
            events: this.props.events
        }
    }

    updateSearchResult(newEvents: event[]) {
        this.setState((state => ({
            events : newEvents
        })));
    } 

    render() {
        const res = this.state.events.map((e: event) => {
            return SearchItem(e)
        } );
        return (
            <div>
                {res}
            </div>
        );
    }
}