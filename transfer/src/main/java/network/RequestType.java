package network;

import java.io.Serializable;

public enum RequestType implements Serializable {
    
    //sve sistemske operacije
    LOGIN,
    SAVE_FAMILY_TREE,
    GET_NODES_OF_TREE,
    GET_ALL_TREES,
    SAVE_NODE,
    LOGOUT,
    UPDATE_NODE,
    GET_TREES_OF_USER,
    DELETE_FAMILY_TREE,
    DELETE_NODE,
    GET_NODES_FOR_UPDATE,
    GET_FAMILY_TREE_ROOT,
    FIND_KINSHIP,
    GET_NODE_INFO
}
