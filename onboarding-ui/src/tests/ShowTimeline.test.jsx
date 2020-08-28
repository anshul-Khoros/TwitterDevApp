import React from 'react';
import { shallow } from 'enzyme';
import { getTimeline } from '../apis/apiRequest';
import ShowTimeline from '../components/ShowTimeline.jsx';


describe("ShowTimeline", () => {
    it("should test ShowTimeline endpoint", () => {
        return getTimeline()
        .then((res)=>{
           expect(true).toBe(true);
        })
        .catch(e=>{
            console.log("got err", e);
           expect(true).toBe(false);
        })
    })
})