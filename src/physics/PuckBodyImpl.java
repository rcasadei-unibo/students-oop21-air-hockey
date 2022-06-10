package physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class PuckBodyImpl extends RigidBodyImpl implements PuckBody {
	
    private final float radius;
    
	/**
	 * Puck body generator.
	 * @param radius of the body
	 * @param pos Position to be spawned.
	 * @param physicsWorld World to be spawned in
	 */
    public PuckBodyImpl(final float radius, final Vec2 pos, final Physics2D physicsWorld) {
        this.radius = radius;
        
    	this.setWorld(physicsWorld.getWorld());
    	this.setBodyType(BodyType.DYNAMIC);
    	this.setStartPositionDef(pos);
    	this.setLinearDampingDef(0.2f);
    	this.configBodyDef();
        
        CircleShape shape = new CircleShape();
        shape.m_radius = getRadius();
        
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 2.0f;
        fixture.friction = 0.5f;
        
        Body playerBody = physicsWorld.getWorld().createBody(getBodyDef());
        physicsWorld.addRigidBody(this);
        playerBody.createFixture(fixture);
        this.setBody(playerBody);
    }
    
    public Vec2 getNextPos() {
        return new Vec2(getBody().getLinearVelocity().x + getPosition().x, getBody().getLinearVelocity().y + getPosition().y);
    }

    public float getRadius() {
        return this.radius;
    } 
    
}
