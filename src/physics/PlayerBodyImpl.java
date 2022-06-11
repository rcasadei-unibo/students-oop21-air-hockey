package physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class PlayerBodyImpl extends RigidBodyImpl implements PlayerBody {
	
    private final float radius;
    
	/**
	 * Player body generator
	 * @param radius of the body
	 * @param pos Position to be spawned
	 * @param physicsWorld World to be spawned in
	 */
    public PlayerBodyImpl(final float radius, final Vec2 pos, final Physics2D physicsWorld) {
    	this.radius = radius;
    	
        this.setBodyType(BodyType.DYNAMIC);
        this.setStartPositionDef(pos);
        this.setLinearDampingDef(0.0f);
        this.configBodyDef();
        
        CircleShape shape = new CircleShape();
        shape.m_radius = radius;
        
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 0.6f;
        fixture.friction = 0.8f;
        fixture.restitution = 0.7f;
        
        // Bit mask for mid arena fixture collision
        fixture.filter.categoryBits = 0x0002;
        
        Body playerBody = physicsWorld.getWorld().createBody(getBodyDef());
        physicsWorld.addRigidBody(this);
        playerBody.createFixture(fixture);
        this.setBody(playerBody);
    }
    
    public float getRadius() {
        return this.radius;
    }
    
}
