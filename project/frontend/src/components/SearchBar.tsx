import {useEffect, useState } from "react";
import { Button, ListGroup, ListGroupItem } from 'react-bootstrap';
import '../styles/searchBar.css';
//import {SearchList} from './SearchList';
import { event } from "./Models";
import axios from 'axios';
import { render } from "@testing-library/react";
import { SearchItem } from "./SearchItem";

declare global {
    var searchResult: [];
}
/*
function useForceUpdate(result : event[]) {

    const [searchResult, setSearchResult] = useState({
        result: [] as event[]
    });

    return () => setSearchResult(result => searchResult);
}
*/
/**
 * Search for the search parameter and returns a string of matching events
 * @returns a string of matching events
 * getInputValue recives the input from the search bar
 * startSearch sends the search parameter to the backend and return the matching events
 */
export default function SearchBar() {
    
    const [searchParam, setSearchParam] = useState('');
    const [searchResult, setSearchResult] = useState({
        result: [] as event[]
    });

    //const forceUpdate = useForceUpdate();

    useEffect(() => {
        const startSearch = 
        axios.get('http://localhost:8080/search?param='+searchParam).then(res => { //searchParam
            const allResults = res.data
            //console.log(allResults)
            //setSearchResult({result: allResults})
            //console.log(searchResult)
            //SearchList(searchResult.result)
            globalThis.searchResult = allResults
            //useForceUpdate(allResults);
        })
    })


    return (
        <section>
            <div className="search">
            <input className="input-text" type="search" placeholder="Sökord..." onChange= {(e) => setSearchParam(e.target.value)}/>
            </div>
            {SearchList()} 
        </section>
      );
    

}

export function SearchList() : JSX.Element[] {
    
    const res = globalThis.searchResult?.map((e: event) => {

        return SearchItem(e)
    })
    return res
}