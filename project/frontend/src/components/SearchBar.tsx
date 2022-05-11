import {useEffect, useState } from "react";
import '../styles/searchBar.css';
import axios from 'axios';

export default function SearchBar() {

    // working!!  Nu funkasr det
    /*
    useEffect(()=> {
        fetch('http://127.0.0.1:8080/search').then(resp=>resp.text())
        .then(resp => {
            console.log('eventsting from api: ' + resp)
        })
    }, [])
    */
    const [appState, setAppState] = useState({
        loading: false,
        result: String as any,
        searchParam: String as any
    });
    
    const getInputValue = (event: { target: { value: any; }; })=>{
    // show the user input value to console
        const userValue = event.target.value;

        console.log(userValue);
    }; 
      
    useEffect(() => {
        setAppState({loading: true, result: "", searchParam: getInputValue});
        // const apiUrl ='http://127.0.0.1:8080/search?param';
        
        axios.get('http://localhost:8080/search?param=p' ).then(res => {
            const allResults = res.data
            console.log(allResults)
            setAppState({ loading: false, result: allResults, searchParam: getInputValue});
        })
    }, [setAppState])
   
    return (
    <div className="SearchBar">
        <input className="InputText" type="search" placeholder="Sökord..." onChange={getInputValue}  />
    </div>
    );

     /*
    return (
        <div className="SearchBar">
            <input className="InputText" type="search" placeholder="Sökord..." />
        </div>
        );
        */
}