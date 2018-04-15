package net.floodlightcontroller.routing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.floodlightcontroller.routing.web.serializers.VirtualInterfaceSerializer;
import org.projectfloodlight.openflow.types.IPv4Address;
import org.projectfloodlight.openflow.types.IPv4AddressWithMask;
import org.projectfloodlight.openflow.types.MacAddress;

/**
 * @author Qing Wang (qw@g.clemson.edu) at 12/29/17
 */
@JsonSerialize(using = VirtualInterfaceSerializer.class)
public class VirtualGatewayInterface {
    private final String name;
    private MacAddress mac;
    private IPv4AddressWithMask iPv4AddressWithMask;

    @JsonCreator
    public VirtualGatewayInterface(@JsonProperty("interface-name") String name,
                                      @JsonProperty("interface-mac") String mac,
                                      @JsonProperty("interface-ip") String ipWithMask) {
        this.name = name;
        this.mac = MacAddress.of(mac);
        this.iPv4AddressWithMask = IPv4AddressWithMask.of(ipWithMask);
    }

    public MacAddress getMac() {
        return mac;
    }

    public IPv4Address getIp() {
        return iPv4AddressWithMask.getValue();
    }

    public IPv4AddressWithMask getIPWithMask() { return iPv4AddressWithMask; }

    public String getInterfaceName() { return name; }

    public void setMac(MacAddress mac) {
        this.mac = mac;
    }

    public void setIp(IPv4AddressWithMask iPv4AddressWithMask) {
        this.iPv4AddressWithMask = iPv4AddressWithMask;
    }

    public boolean containsIP(IPv4Address ip) {
        return iPv4AddressWithMask.contains(ip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VirtualGatewayInterface that = (VirtualGatewayInterface) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VirtualGatewayInterface{" +
                "name='" + name + '\'' +
                ", mac=" + mac +
                ", iPv4AddressWithMask=" + iPv4AddressWithMask +
                '}';
    }

}
