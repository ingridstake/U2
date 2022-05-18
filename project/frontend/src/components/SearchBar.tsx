import {Component} from "react";
import '../styles/searchBar.css';
import axios from 'axios';
import { event } from "./Models";
import React from "react";
import SearchResultList from "./SearchList";

type search = {
    searchParam: string;
    events: event[];
}

/**
 * Search for the search parameter and returns a string of matching events
 * @returns a string of matching events
 * getInputValue recives the input from the search bar
 * startSearch sends the search parameter to the backend and return the matching events
 */
export default class SearchBar extends Component<search, event[]> implements JSX.Element {
    type: any;
    key: React.Key | null = null;
    searchResult: search = {searchParam:"", events: []};
    searchParam: string = "";

    constructor(props: search){
        super(props);
        this.startSearch = this.startSearch.bind(this);
        this.setSearchParam = this.setSearchParam.bind(this);
    }

    setSearchParam(newParam: string){
        if(newParam.valueOf() != this.searchParam?.valueOf()){
            this.searchParam = newParam;
            this.startSearch();
        }
    }
    
    startSearch(){
        axios.get('http://localhost:8080/search?param='+this.searchParam).then(res => {
            this.searchResult.events = res.data as event[];
            console.log(res.data);
        })
        this.render();
        debugger;
        this.forceUpdate();
    }

    render(){
        return (
            <div>
                <div className="search-bar">
                    <input className="input-text" type="search" placeholder="Sökord..." value={this.searchParam} onChange={(e) => this.setSearchParam(e.target.value)}  />
                </div>
                <SearchResultList events={this.searchResult.events} />
            </div>
        );
    }     
}
