import React from 'react';
import { shallow } from 'enzyme';
import HelloReact from '../components/HelloReact.jsx';


describe("HelloReact", () => {
    it("should render component", () => {
        const wrapper = shallow(<HelloReact />);
        expect(wrapper.find('div').length).toEqual(1);
        expect(true).toBe(true);
    })
})