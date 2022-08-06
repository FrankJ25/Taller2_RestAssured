package factoryRequest;

public class FactoryRequest {

    public static IRequest make(String typeRequest){
        IRequest request;
        switch (typeRequest.toLowerCase()){
            case "get":
                request = new RequestGET();
                break;
            case "delete":
                request = new RequestDELETE();
                break;
            case "post":
                request = new RequestPOST();
                break;
            default:
                request = new RequestPUT();
                break;
        }
        return request;
    }

}
