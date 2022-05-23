import {Component} from "react";
import '../styles/searchBar.css';
import axios from 'axios';
import { event } from "./Models";
import React from "react";
import {SearchItem} from "./SearchItem";

type search = {
    searchParam: string;
    events: event[];
}

type searchResult = {
    searchP : search;
    eventHits: JSX.Element[]
}

/**
 * Search for the search parameter and returns a string of matching events
 * @returns a string of matching events
 * getInputValue recives the input from the search bar
 * startSearch sends the search parameter to the backend and return the matching events
 */
export default class SearchBar extends Component<search, searchResult> implements JSX.Element {
    type: any;
    key: React.Key | null = null;
    search: search = {searchParam:"", events: []};
    state: searchResult = {eventHits: [], searchP: this.search};

    constructor(props: search){
        super(props);
        this.startSearch = this.startSearch.bind(this);
        this.setSearchParam = this.setSearchParam.bind(this);
    }

    setSearchParam(newParam: string){
        this.search.searchParam = newParam;
        this.forceUpdate();
        if(this.search.searchParam.length > 2){
            this.startSearch();
                
            this.render()
            this.forceUpdate()
        } else {
            this.state.eventHits = [];
        }
    }
    
    startSearch(){
        axios.get('http://localhost:8080/search?param='+this.search.searchParam).then(res => {
            this.search.events = res.data as event[];
            this.state.eventHits = res.data.map((e: event) => {
                return SearchItem(e)
            });
            this.render();
            this.forceUpdate();
        })
    }

    render(){
        return (
            <div>
                <div className="search-bar">
                    <input className="input-text" type="search" placeholder="Sökord..." value={this.search.searchParam} onChange={(e) => this.setSearchParam(e.target.value)}  />
                </div>
                <div className="scrollable-div">
                    {this.state.eventHits}
                </div> 
            </div>
        );
    }     
}
