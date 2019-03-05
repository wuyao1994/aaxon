import React from 'react'
import PropTypes from 'prop-types'
import { Menu, Icon } from 'antd'
import { Link } from 'react-router-dom'
import { arrayToTree } from '../../utils'

const { SubMenu } = Menu
const Menus = ({
  menu,
}) => {
  const menuTree = arrayToTree(menu.filter(_ => _.menuLevel !== '-1'), 'id', 'mpid')

  const getMenus = (menuTreeN) => {
    return menuTreeN.map((item) => {
      if (item.children) {
        return (
          <SubMenu
            key="{item.id}"
            title={<span>{item.icon && <Icon type={item.icon} />}{item.name}</span>}
          >
            {getMenus(item.children)}
          </SubMenu>
        )
      }
      return (
        <Menu.Item key={item.id}>
          <Link to={item.router || '#'}>
            {item.icon && <Icon type={item.icon} />}
            {item.name}
          </Link>
        </Menu.Item>
      )
    })
  }
  const menuItems = getMenus(menuTree)

  return (
    <Menu mode="inline">
      {menuItems}
    </Menu>
  )
}
Menus.propTypes = {
  menu: PropTypes.array,
}
export default Menus

