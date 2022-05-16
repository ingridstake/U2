import {useEffect, useState } from "react";
import { Button, ListGroup, ListGroupItem } from 'react-bootstrap';
import '../styles/searchBar.css';
import {SearchList} from './SearchList';
import { event } from "./Models";
import axios from 'axios';


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

    const getInputValue = (e: { target: { value: any; }; }) => {
        const userVal = e.target.value;
        setSearchParam(userVal)        
        //console.log(searchParam)
    }
      
    useEffect(() => {
        const startSearch = 
        axios.get('http://localhost:8080/search?param='+searchParam).then(res => { //searchParam
            const allResults = res.data
            //console.log(allResults)
            setSearchResult({result: allResults})
            console.log(searchResult)
            //SearchList(searchResult.result)
        })
    })

    /*
    const searchList =
        <ListGroup>
            {searchResult.result.map(r => (
                console.log(r.name)
            ))}

        </ListGroup>
        */

    /*
    const searchField = 
        <div> className="listContainer"
            <div className="search-bar">
                <input className="input-text" type="search" placeholder="Sökord..." onChange={getInputValue}  />

            </div>
        </div>
        */
    /*
    return (

        <div className="search-bar">
            <input className="input-text" type="search" placeholder="Sökord..." onChange={getInputValue} />
        </div>
            
        </div>
          {searchResult.result.length > 0 && (
            <div className="dataResult">
              {searchResult.result.map((value, key) => {
                return (
                  <a className="dataItem" >
                    <p>{value.title} </p>
                  </a>
                );
              })}
            </div>
          )}

    );
    */
   // {getInputValue} 
    
    return (
        <section>
            <div className="search">
            <input className="input-text" type="search" placeholder="Sökord..." onChange= {(e) => setSearchParam(e.target.value)}/>
            </div>
            {SearchList(searchResult.result)} 
        </section>
      );
    

}