package com.water.thread.wblClass29;

/**
 * @Description:CopyOnWrite:适用场景：1、对性能要求高；2、读多写少；3、弱一致性
 * @Author: pengzuyao
 * @Time: 2019/06/27
 */
public final class Router {

    private final String ip;
    private final Integer port;
    private final String iface;

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getIface() {
        return iface;
    }

    public Router(String ip , Integer port , String iface){
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    //重写equals方法

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Router){
            Router r = (Router) obj;
            return iface.equals(r.iface) &&
                    ip.equals(r.ip) &&
                    port.equals(port);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
